(ns hangman.core
  (:gen-class))

(require '[clojure.string :as clj :only (join)])

(def life-total 6)

(defn you-lose [] (println "You lose :("))

(defn you-win [] (println "You win :)"))

(defn remaining-letters 
  [secret-word attempts]
  (remove (fn [letter] (contains? attempts (str letter))) secret-word))

(defn discovered-word? 
  [secret-word attempts] 
  (empty? (remaining-letters secret-word attempts)))

(defn print-current-attempts 
  [secret-word attempts] 
  (println (map
    (fn [letter]
    (if (contains? attempts (str letter))
      (clj/join [letter " "])
      "_ "))
    secret-word)))

(defn print-remaining-life 
  [current-life] 
  (println (clj/join ["Remaining Life: " current-life])))

(defn new-attempt [attempts] 
  (do
  (println "Insert a letter:")
  (conj attempts (read-line))))

(defn run-game [life-count secret-word attempts] 
  (if (= life-count 0)
    (you-lose)
    (if (discovered-word? secret-word attempts)
      (you-win)
      (do
        (print-current-attempts secret-word attempts)
        (print-remaining-life (dec life-count))
        (if (.contains secret-word (clj/join attempts))
          (recur life-count secret-word (new-attempt attempts))
          (recur (dec life-count) secret-word (new-attempt attempts)))))))

(defn -main
  [& args]
  (run-game life-total "TEST" #{}))