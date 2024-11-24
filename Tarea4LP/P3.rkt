#lang scheme

;; La función evaluador toma una lista de `funciones` y una lista de `numeros` y aplica las funciones rotadas a cada número,
;; de modo que cada número se evalúa con una lista de funciones en un orden rotado como indica el pdf.
;;
;; funciones : Lista de funciones a aplicar.
;; numeros : Lista de números a los que se les aplicarán las funciones de forma procedural.
(define (evaluador funciones numeros)
  
  ;; Mueve el primer elemento de la lista al final.
  ;;
  ;; lista : Lista en la cual se rotará el primer elemento al final.
  (define (rotar lista)
    (if (null? lista)
        '()
        (append (cdr lista) (list (car lista))))) 

  ;; Rota la lista `n` veces moviendo el primer elemento al final de forma recursiva.
  ;;
  ;; lista : Lista que se va a rotar.
  ;; n : Número de veces que se rota la lista.
  (define (rotar-n lista n)
    (if (= n 0)
        lista
        (rotar-n (rotar lista) (- n 1))))

  ;; Aplica cada función de la lista `funciones` al valor `valor`, encadenando el resultado de una función
  ;; como entrada para la siguiente.
  ;;
  ;; funciones : Lista de funciones a aplicar secuencialmente.
  ;; valor : Valor inicial al que se aplicarán las funciones.
  (define (aplicar-funciones funciones valor)
    (if (null? funciones)
        valor
        (let ((resultado ((car funciones) valor)))
          (aplicar-funciones (cdr funciones) resultado))))

  ;; Evalúa cada elemento de la lista `numeros` aplicando las funciones rotadas a cada número.
  ;; Genera una lista con los resultados de aplicar las funciones a cada número.
  ;;
  ;; funciones : Lista de funciones que se rotarán para aplicar a cada número.
  ;; numeros : Lista de números a los que se aplicarán las funciones.
  ;; indice : Índice actual que se usa para calcular el número de rotaciones de las funciones.
  (define (evaluar funciones numeros indice)
    (if (null? numeros)
        '() 
        (letrec ((funciones-rotadas (rotar-n funciones (- indice 1)))
              (valor (car numeros))
              (resultado (aplicar-funciones funciones-rotadas valor)))
          (cons resultado
                (evaluar funciones (cdr numeros) (+ indice 1))))))

  (evaluar funciones numeros 1))
