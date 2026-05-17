# Pasos manuales para Actividad 1 y Actividad 2

## Actividad 1: inserciones AVL

Secuencia:

```text
30, 15, 20, 50, 40, 60, 70, 10, 25, 45, 55, 65, 75
```

Procedimiento para hacerlo a mano:

1. Inserta cada clave como en un BST: menor a la izquierda, mayor a la derecha.
2. Despues de cada insercion, calcula el factor de equilibrio con:

```text
bf = altura(subarbol derecho) - altura(subarbol izquierdo)
```

3. Si todos los `bf` quedan entre `-1, 0, 1`, no rotas.
4. Si aparece `bf = -2`, el desbalance es hacia la izquierda:
   - Izquierda-Izquierda: rotacion simple derecha, `RSR`.
   - Izquierda-Derecha: rotacion doble derecha, `RDR`.
5. Si aparece `bf = 2`, el desbalance es hacia la derecha:
   - Derecha-Derecha: rotacion simple izquierda, `RSL`.
   - Derecha-Izquierda: rotacion doble izquierda, `RDL`.
6. Dibuja el arbol despues de cada reestructuracion.

Tabla para completar:

```text
N | Insercion K | Nodo X | Tipo de desbalance   | Rotacion | Nodo Y
1 | 20          | 30     | Izquierda-Derecha    | RDR      | 20
2 | 40          | 30     | Derecha-Izquierda    | RDL      | 40
3 | 60          | 20     | Derecha-Derecha      | RSL      | 40
4 | 70          | 50     | Derecha-Derecha      | RSL      | 60
```

Arbol final:

```text
                40
         20            60
     15      30    50      70
   10      25    45  55  65  75
```

## Actividad 2: eliminaciones AVL

Claves a eliminar:

```text
12, 33, 46, 59, 45, 56
```

Arbol inicial de la figura 8.10:

```text
                         33
              20                    45
        12          26         41          56
      6   15      24         35  44      48  59
            17                  38      46 53  65
                                             50
```

Procedimiento para hacerlo a mano:

1. Ubica la clave que se elimina.
2. Clasifica el caso BST:
   - Caso 1: nodo hoja.
   - Caso 2: nodo con un hijo.
   - Caso 3: nodo con dos hijos.
3. Si es Caso 3, reemplaza la clave por su sucesor inorden, que es el menor del subarbol derecho.
4. Elimina fisicamente el nodo reemplazado.
5. Sube hacia la raiz revisando los factores de equilibrio.
6. Si algun nodo queda con `bf = 2` o `bf = -2`, aplica la rotacion correspondiente.
7. Dibuja el arbol despues de cada eliminacion.

Tabla obtenida con el arbol de la figura:

```text
K  | Caso BST | Sucesor | Desbalance | Nodo X | Rotacion | Nodo Y | Otra rotacion
12 | Caso 3   | 15      | Si          | 33     | RSL      | 45     | No
33 | Caso 3   | 35      | No          | -      | -        | -      | No
46 | Caso 1   | -       | Si          | 48     | RDL      | 50     | No
59 | Caso 2   | -       | No          | -      | -        | -      | No
45 | Caso 3   | 48      | No          | -      | -        | -      | No
56 | Caso 3   | 65      | Si          | 65     | RDR      | 53     | Si
```

Nota importante: la guia muestra una fila de ejemplo para `12` que no coincide con la figura, porque en la figura el nodo `12` tiene dos hijos (`6` y `15`). Si tu docente pide copiar exactamente esa fila de ejemplo, usa la de la guia; si evalua segun la figura, usa la tabla anterior.
