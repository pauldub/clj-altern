# Alter

A Clojure library designed to alternate text and comments from persian texts.

## Usage

Put html files under the `resources/`, in this example we have `Text.html` and `Commentary.html` in the directory.

```clojure
(use 'altern.core)
(require ['net.cgrand.enlive-html :as 'html])

;; Load both files into variables (this can take some time if the files are large)
(def text (html-resource "Text.html"))
(def commentary (html-resource "Commentary.htlm"))

;; Alternate text and comments, and then save the result in the file Alternate.html
(alternate-and-save text commentary "Alternate.html")
```

## License

Copyright © 2014 Paul d'Hubert

Distributed under the GPL v2 License.
