(ns clojure-hangman.core
  (:gen-class))

(def life-total 5)

(defn you-lose [] (println "You lose :("))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println life-total))
