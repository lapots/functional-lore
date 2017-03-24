(ns com.lapots.functional.clojure.edgar.core
    (:gen-class)
    (:require [clj-time.core :as tc]
              [clj-time.periodic :as tp]
              [clj-time.coerce :as tco]))

(defn random-in-range [lower upper]
    (let [r (rand upper)]
        (if (>= r lower)
            r
            (+ (rand (- upper lower))
                lower))))

(defn stochastic-k [last-price low-price high-price]
    (let [hlrange (- high-price low-price)
          hlmidpoint (/ hlrange 2)
          numerator (if (> last-price hlmidpoint)
                       (- last-price hlmidpoint)
                       (- hlmidpoint low-price))]
        (/ numerator hlrange)))

(defn break-local-minima-maxima [k]
    (as-> k k
        (if (<= (int (+ 0.95 k)) 0)
          (+ 0.15 k) k)
        (if (>= k 1)
          (- k 0.15) k)))

(defn generate-prices
    ([low high]
        (generate-prices (random-in-range low high)))
    ([last-price]
        (iterate (fn [{:keys [last]}]
                    (let [low (- last 5)
                          high (+ last 5)
                          k (stochastic-k last low high)
                          plus-OR-minus (rand-nth [- +])
                          kPM (if (= plus-OR-minus +)
                                (+ 1 (break-local-minima-maxima k))
                                (- 1 (break-local-minima-maxima k)))
                          newprice (* kPM last)
                          newlow (if (< newprice low) newprice low)
                          newhigh (if (> newprice high) newprice high)]
                    {:last newprice}))
                  {:last last-price})))

(defn generate-timeseries
    ([pricelist]
        (generate-timeseries pricelist (tc/now)))
    ([pricelist datetime]
        (->> (map (fn [x y] [x y])
                (map (fn [x] {:time x}) (iterate #(tc/plus % (tc/seconds (rand 4))) datetime))
                (map (fn [x] {:price x}) pricelist))
             (map (fn [x] (merge (first x) (second x)))))))

(defn -main [& args]
    (def pricelist (generate-prices 5 15))
    (def result (take 40 (generate-timeseries pricelist)))
    (println result))