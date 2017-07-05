(ns hangman.core
  (:gen-class))

(def life-total 6)

(defn you-lose [] (println "You lose :("))

(defn run-game [life-count] 
  (if (= life-count 0)
    (you-lose)
    (do
      (println life-count)
      (run-game (dec life-count)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-game life-total))
