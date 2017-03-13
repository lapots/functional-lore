(ns com.lapots.functional.clojure.script
    (:import
        (javax.swing JApplet JPanel JLabel JFrame))
    (:gen-class
        :post-init post-init
        :main -main))

(compile 'com.lapots.functional.clojure.applet)

(defn -main [& args]
  (let [applet (new com.lapots.functional.clojure.applet)]
      (doto (JFrame. "Ants")
          (.add (.getContentPane applet))
          (.pack)
          (.setLocationByPlatform true)
          (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
          (.setVisible true))
))
