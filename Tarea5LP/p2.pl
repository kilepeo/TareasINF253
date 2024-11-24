% Definición de los puentes con sus costos.
puente(p1, c, 4).
puente(p1, p4, 1).
puente(p2, p1, 3).
puente(p2, p3, 7).
puente(p3, c, 2).
puente(p4, c, 3).
puente(p5, p1, 7).
puente(p6, p2, 2).
puente(p7, p2, 3).
puente(p7, p6, 4).
puente(p8, p3, 8).
puente(p8, p9, 3).
puente(p9, p10, 10).
puente(p10, p3, 3).
puente(p10, p4, 6).
puente(p11, p4, 7).
puente(p11, p12, 3).
puente(p12, p5, 2).

% NOMBRE DE LA FUNCIÓN.
% camino/2
%
% Parámetros que recibe:
% - X: Nodo inicial.
% - [X|Res]: Lista que representa el camino desde el nodo inicial hasta
%            el nodo de destino.
%
% returns:
% - true si existe un camino desde el nodo inicial hasta el nodo de destino.
% - false en caso contrario.
%
% Descripción breve de lo que hace:
% Determina si existe un camino entre un nodo inicial y un nodo de
% destino. Tiene  casos, el caso base si es que el nodo inicial es "c"
% entonces el camino simplemente es c, mientras que el caso recursivo
% buscará un camino desde el nodo X , enconrará un nodo Y conectado a X
% mediante un punte y llamará recursivamente para buscar el camino desde
% Y.
camino(c, [c]).

camino(X, [X|Res]) :-
    puente(X, Y, _),
    camino(Y, Res).

% NOMBRE DE LA FUNCIÓN.
% combustible/3
%
% Parámetros que recibe:
% - X: Nodo inicial.
% - Z: Cantidad de combustible disponible.
% - [[X, Z]|Res]: Lista de arcos (pares [nodo, costo]) que representan
%                 el camino con el consumo de combustible.
%
% returns:
% - true si existe un camino desde el nodo inicial hasta el nodo de
% destino con la cantidad de combustible disponible.
%- false en caso contrario.
%
% Descripción breve de lo que hace:
% Verifica si existe un camino desde un nodo inicial hasta el nodo de destino
% tal que la cantidad de combustible disponible sea suficiente para
% cubrir los costos de los puentes. En este caso tambien existen el caso
% base y recursivo, Si el nodo inicial es C, el camino será [[c, Z]]. en
% caso recursivo, buscará un camino con suficiente combustible desde X y
% encontrará un nodo Y conectado a X mediante un puente con costo W,
% verificará si es que se puede realizar el salto y llamará
% recursivamente con el combustible restante.
combustible(c, Z, [[c, Z]]).

combustible(X, Z, [[X, Z]|Res]) :-
    puente(X, Y, W),
    Z >= W,
    Zres is Z - W,
    combustible(Y, Zres, Res).
