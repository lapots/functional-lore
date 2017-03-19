(ns com.lapots.functional.clojure.edgar.core
    (:gen-class)
    (:require [clj-time.core :as tc]
              [clj-time.periodic :as tp]
              [clj-time.coerce :as tco]))

(defn generate-prices [lower-bound upper-bound]
    (filter (fn [x] (>= x lower-bound))
        (repeatedly (fn [] (rand upper-bound)))))

(defn generate-timeseries [pricelist]
    (map (fn [x y]
            {:time x :price y})
          (iterate inc 0)
         pricelist))

(defn -main [& args]
    (def pricelist (generate-prices 12 35))
    (println (take 10 (generate-timeseries pricelist))))