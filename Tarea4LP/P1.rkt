#lang scheme

;; La función buscador actuará como un finder del elemento solocitado dentro de una lista, este será capaz de diferenciar
;; Sobre distintos elementos, sean listas, funciones, numeros, etc. Si el valor no se encuentra, retornará -1.
;;
;; Lista : Lista en la cual se buscará el valor 'buscado'
;; buscado : Valor de interés del usuario al ejecutar la función.
(define buscador
  (lambda (Lista buscado)
    (letrec ((f (lambda (L x i)
                  (cond
                    ((null? L) -1)                    
                    ((equal? (car L) x) i)           
                    (else (f (cdr L) x (+ i 1)))))))  
      (f Lista buscado 1))))                         
