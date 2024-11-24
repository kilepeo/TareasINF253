% NOMBRE DE LA FUNCION.
% es_palindromo
%
% Parametros que recibe:
% - Lista: Lista de elementos que se desea verificar si es un palíndromo en el rango especificado.
% - Inicio: Índice inicial del segmento que se está evaluando.
% - Fin: Índice final del segmento que se está evaluando.
%
% returns:
% - true si el segmento de la lista delimitado por los índices Inicio y Fin es un palíndromo.
% - false en caso contrario.
%
% Descripción breve de lo que hace:
% Verifica recursivamente si un segmento de una lista es un palíndromo. Compara los elementos
% en las posiciones Inicio y Fin, avanzando hacia el centro del segmento. El proceso termina
% exitosamente si todos los pares coinciden.
es_palindromo(Lista, Inicio, Fin) :-
    Inicio =< Fin,                                 % Mientras Inicio sea menor o igual a Fin, continuamos
    nth1(Inicio, Lista, ElemInicio),               % Extraemos el elemento en la posición Inicio
    nth1(Fin, Lista, ElemFin),                     % Extraemos el elemento en la posición Fin
    ElemInicio =:= ElemFin,                        % Comparamos los elementos; deben ser iguales
    SiguienteInicio is Inicio + 1,                 % Incrementamos Inicio
    SiguienteFin is Fin - 1,                       % Decrementamos Fin
    es_palindromo(Lista, SiguienteInicio, SiguienteFin).   % Llamada recursiva con los nuevos límites

% NOMBRE DE LA FUNCION.
% es_palindromo (Caso base)
%
% Parametros que recibe:
% - _: Lista (no es relevante en este caso base, por lo que se ignora).
% - Inicio: Índice inicial del segmento que se está evaluando.
% - Fin: Índice final del segmento que se está evaluando.
%
% returns:
% - true, ya que Inicio > Fin indica que todo el segmento ha sido validado.
%
% Descripción breve de lo que hace:
% Define el caso base de la función recursiva, que ocurre cuando Inicio es mayor que Fin.
% En este punto, se asume que todo el segmento ha sido verificado exitosamente.
es_palindromo(_, Inicio, Fin) :-
    Inicio > Fin.

% NOMBRE DE LA FUNCION.
% esPalindroma
%
% Parametros que recibe:
% - Lista: Lista de elementos que se desea verificar si es un palíndromo en el rango especificado.
% - [L, R]: Lista con dos elementos que representan el índice inicial (L) y el índice final (R) del segmento a evaluar.
%
% returns:
% - true si el segmento de la lista delimitado por L y R es un palíndromo.
% - false en caso contrario.
%
% Descripción breve de lo que hace:
% Función principal que sirve como interfaz para verificar si un segmento de una lista es un palíndromo.
% Invoca a es_palindromo/3 con los índices inicial y final del segmento.
esPalindroma(Lista, [L, R]) :-
    es_palindromo(Lista, L, R).
