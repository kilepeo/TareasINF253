#lang scheme

;; Calcula el factorial de un número `n`.
;;
;; n : Número entero no negativo del cual se calculará el factorial.
(define (factorial n)
  (if (= n 0)
      1
      (* n (factorial (- n 1)))))

;; Calcula la aproximación del seno usando una serie de Taylor de `n` términos.
;;
;; n : Número de términos en la serie de Taylor o nivel de precisión entregado por el usuario.
;; x : Valor en el cual se evaluará la función seno.
(define (taylorSenoSimple n x)
  (if (= n 0)
      x
      (+ (/ (* (expt -1 n) (expt x (+ (* 2 n) 1))) (factorial (+ (* 2 n) 1)))
         (taylorSenoSimple (- n 1) x))))

;; Calcula la aproximación del coseno usando una serie de Taylor de `n` términos, de manera que utiliza recursión en cola.
;;
;; n : Número de términos en la serie de Taylor (nivel de precisión).
;; x : Valor en el cual se evaluará la función coseno.
(define (taylorCosenoCola n x)
  ;; Función auxiliar `cos` que usa recursión en cola para calcular la serie de Taylor.
  ;;
  ;; i : Término actual en la serie.
  ;; actual : Valor acumulado del término actual.
  ;; total : Suma acumulada de los términos evaluados hasta el momento.
  (define (cos i actual total)
    (if (> i n)
        total
        (cos (+ i 1)
             (* -1 actual (/ (* x x) (* (+ (* 2 i) 1) (+ (* 2 i) 2))))
             (+ total actual))))
  (cos 0 1 0))
