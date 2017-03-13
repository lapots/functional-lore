(ns com.lapots.functional.clojure.edgar.core
    (:gen-class))

(defn generate-prices [lower-bound upper-bound]
    (filter (fn [x] (>= x lower-bound))
        (repeatedly (fn [] (rand upper-bound)))))

(defn -main [& args]
    (println (take 10 (generate-prices 12 35))))