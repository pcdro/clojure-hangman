(ns hangman.core
  (:gen-class))

(use '[clojure.string :only (join)])

(def life-total 6)

(defn you-lose [] (println "You lose :("))

(defn you-win [] (println "You win :)"))

(defn print-remaining-life 
  [current-life] 
  (println (clojure.string/join ["Remaining Life: " current-life])))

(defn remaining-letters 
  [secret-word hits]
  (remove (fn [letter] (contains? hits (str letter))) secret-word))

(defn discovered-word? 
  [secret-word hits] 
  (empty? (remaining-letters secret-word hits)))

(defn run-game [life-count secret-word hits] 
  (if (= life-count 0)
    (you-lose)
    (if (discovered-word? secret-word hits)
      (you-win)
      (do
        (println "Guess a letter!")
        (print-remaining-life (dec life-count))
        (run-game (dec life-count) secret-word hits)))))

(defn -main
  [& args]
  (run-game life-total "TEST" #{"A", "B"}))
