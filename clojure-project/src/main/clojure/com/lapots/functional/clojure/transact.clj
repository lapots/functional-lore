    (ns com.lapots.functional.clojure.transact
        (:gen-class))

    (defn transfer [from to amount]
        (alter
            (.balance from) - amount)
         (alter
            (.balance to) + amount))

    (defrecord Account [balance])

    (defn -main [& args]
        (def account1 (Account. (ref 100)))
        (def account2 (Account. (ref 100)))
        (def trx-agent (agent 0))

        (future
            (dosync
                (send-off "T2 transfer" println)
                (Thread/sleep 3000)
                (transfer account1 account2 10)))

        (dosync
            (println "T1 transfer")
            (transfer account1 account2 10))

        (Thread/sleep 10000)
        (shutdown-agents)
    )