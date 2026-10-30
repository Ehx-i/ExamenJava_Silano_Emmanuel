# Examen Final: Lógica de Programación I (Java)

Solución del examen final del curso **Lógica de Programación I**, del programa **Desarrolla+ Colombia** (Ada).

**Autor:** Emmanuel Silano

## Descripción

El examen tiene dos problemas que se resuelven únicamente con variables, constantes `final`, `printf`, funciones con retorno, condicionales, `enum`, `switch`, vectores, matrices y ciclos `for`. No se usan `Arrays.sort`, `ArrayList` ni otras librerías.

| Problema | Tema | Nivel | Puntaje |
|----------|------|-------|---------|
| A | Reporte de ventas semanal | Intermedio | 40 |
| B | Evaluador del curso | Avanzado | 60 |

### Problema A: Reporte de ventas semanal

Genera un reporte con las ventas de cada día de la semana (en miles de pesos).

- Calcula el total y el promedio diario.
- Clasifica cada día como `ALTA`, `BAJA` o `NORMAL` respecto al promedio (±20 %).
- Cuenta los días que superaron el promedio.
- Identifica el mejor y el peor día usando índices.
- Indica si se cumplió la meta semanal de 10.000 y por cuánto.

### Problema B: Evaluador del curso

Procesa las notas de seis estudiantes en cuatro evaluaciones.

- Calcula el promedio ponderado de cada estudiante.
- Define su estado final (`APROBADO`, `RECUPERACION` o `REPROBADO`) con un `enum`, revisando primero la asistencia.
- Cuenta los estados con un `switch`.
- Analiza cada evaluación: promedio del curso y mejor nota (recorrido de la matriz por columnas).
- Arma el ranking con el método burbuja, ordenando un vector de posiciones (`orden`) en lugar de mover los datos.

## Estructura del proyecto

```
ExamenJava_Apellido_Nombre/
└── src/
    ├── problemaA/
    │   └── Ejercicio1Ventas.java
    └── problemaB/
        └── Ejercicio2Evaluador.java
```

## Ramas

| Rama | Contenido |
|------|-----------|
| `main` | Solución base, siguiendo las instrucciones del examen. |
| `versionMejorada` | Misma solución con mejoras que respetan las reglas del examen (ver abajo). |

### Mejoras en `versionMejorada`

- Constantes `final` en lugar de números "mágicos" (`FACTOR_ALTA`, `FACTOR_BAJA`, `ASISTENCIA_MINIMA`, `NOTA_APROBACION`, `NOTA_RECUPERACION`, `A_PORCENTAJE`).
- `main` dividido en funciones adicionales (`imprimirTabla`, `imprimirResumen`, `imprimirResultados`, `contarEstados`, `analizarEvaluaciones`, `calcularOrden`, `imprimirRanking`).
- Validaciones simples con `IllegalArgumentException`.
- Nombres de variables más claros y comentarios por paso.

## Cómo ejecutarlo

Desde la carpeta `src`:

```bash
# Problema A
javac problemaA/Ejercicio1Ventas.java
java problemaA.Ejercicio1Ventas

# Problema B
javac problemaB/Ejercicio2Evaluador.java
java problemaB.Ejercicio2Evaluador
```

También se puede ejecutar desde IntelliJ IDEA con clic derecho sobre cada clase y *Run*.

> Según la configuración regional del computador, `printf` puede mostrar coma decimal (`1250,50`) en lugar de punto (`1250.50`). Ambas formas son válidas.

## Salida esperada (resumen)

**Problema A:** total semanal `11062.00`, promedio `1580.29`, mejor día Sábado (`2650.50`), peor día Jueves (`760.25`), 3 días sobre el promedio y meta cumplida por `1062.00`.

**Problema B:** 3 aprobados, 1 en recuperación y 2 reprobados. Ranking: Valentina (4.83), Ana (4.30), Luis (4.13), Carlos (3.09), Sofia (2.71) y Mateo (2.12).

## Tecnologías

- Java
- IntelliJ IDEA
