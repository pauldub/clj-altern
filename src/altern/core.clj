(ns altern.core
  (:require 
    [clojure.contrib.seq-utils :as seq-utils]
    [net.cgrand.enlive-html :as html]))

(defn commentary-page-reference [commentary]
  (let [reference (first (html/select commentary [:a]))]
    (list (apply html/text (:content reference)) commentary)))

(defn index-commentary [commentary]
  (->> (html/select commentary [:p])
       (map commentary-page-reference)))

(defn find-commentary [commentary-index reference]
  (seq-utils/find-first 
    #(= (first %) reference) commentary-index))

(defn add-commentary [index]
  (fn [paragraph]
    (let [links (->> (html/select paragraph [:a])
                     (map html/text))
          commentaries (map (partial find-commentary index) links)
          append-to (html/append {:tag :br} (map last commentaries)  {:tag :br})]
      (append-to paragraph))))

(defn alternate [text commentary]
  (let [commentary-index (index-commentary commentary)]
    (html/transform text [:p] (add-commentary commentary-index))))

(defn alternate-and-save [text commentary file]
  (let [html (alternate text commentary)]
    (->> html (html/emit*) (apply str) (spit file))))
