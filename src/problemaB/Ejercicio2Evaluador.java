package problemaB;


public class Ejercicio2Evaluador {

	enum Estado {
		APROBADO, RECUPERACION, REPROBADO
	}
	
	public static final double ASISTENCIA_MINIMA = 0.75;
    public static final double NOTA_APROBACION = 3.0;
    public static final double NOTA_RECUPERACION = 2.5;
    public static final int A_PORCENTAJE = 100;

	public static void main(String[] args) {
		String[] estudiantes = { "Ana", "Luis", "Sofia", "Carlos", "Valentina", "Mateo" };
		String[] evaluaciones = { "Quiz 1", "Quiz 2", "Taller", "Proyecto" };
		double[][] notas = { { 4.5, 4.9, 3.8, 4.2 }, // Ana
				{ 3.5, 4.0, 4.9, 4.0 }, // Luis
				{ 2.5, 3.0, 2.8, 2.6 }, // Sofia
				{ 3.0, 2.5, 3.2, 3.4 }, // Carlos
				{ 5.0, 4.8, 4.6, 4.9 }, // Valentina
				{ 2.0, 2.5, 1.8, 2.2 } // Mateo
		};
		double[] pesos = { 0.20, 0.20, 0.25, 0.35 }; // suman 1.0
		double[] asistencia = { 0.95, 0.70, 0.88, 0.80, 1.00, 0.76 }; // 0.95 = 95%
		// Tu código aquí
		
		 double[] promedios = new double[estudiantes.length];
	     Estado[] estados = new Estado[estudiantes.length];
	     

	     imprimirResultados(estudiantes, notas, pesos, asistencia, promedios, estados);
	     contarEstados(estados);
	     analizarEvaluaciones(estudiantes, evaluaciones, notas);
	     int[] orden = calcularOrden(promedios);
	     imprimirRanking(estudiantes, promedios, orden);
	     
	}

	// Paso 1: llena promedios y estados, e imprime la tabla.
	public static void imprimirResultados(String[] estudiantes, double[][] notas, double[] pesos,
			double[] asistencia, double[] promedios, Estado[] estados) {
		System.out.println("=== RESULTADOS DEL CURSO ===");
		System.out.printf("%-10s %8s %10s  %s%n", "Estudiante", "Promedio", "Asistencia", "Estado");
		for (int i = 0; i < estudiantes.length; i++) {
			promedios[i] = promedioPonderado(notas[i], pesos);
			estados[i] = evaluar(promedios[i], asistencia[i]);
			System.out.printf("%-10s %8.2f %10.0f%%  %s%n", estudiantes[i], promedios[i],
					asistencia[i] * A_PORCENTAJE,
					estados[i]);
		}
	}

	// Paso 2: cuenta con switch en tres contadores.
	public static void contarEstados(Estado[] estados) {
		int aprobados = 0;
		int recuperacion = 0;
		int reprobados = 0;
		for (int i = 0; i < estados.length; i++) {
			switch (estados[i]) {
			case APROBADO:
				aprobados++;
				break;
			case RECUPERACION:
				recuperacion++;
				break;
			case REPROBADO:
				reprobados++;
				break;
			}
		}
		System.out.println();
		System.out.println(
				"Aprobados: " + aprobados + " | Recuperacion: " + recuperacion 
				+ " | Reprobados: " + reprobados);
	}

	// Paso 3: recorre la matriz por columnas.
	public static void analizarEvaluaciones(String[] estudiantes, String[] evaluaciones,
			double[][] notas) {
		System.out.println();
		System.out.println("=== ANALISIS POR EVALUACION ===");
		for (int j = 0; j < evaluaciones.length; j++) {
			
			double suma = 0;
			
			int indiceMejor = 0;
			
			for (int i = 0; i < estudiantes.length; i++) {
				suma += notas[i][j];
				if (notas[i][j] > notas[indiceMejor][j]) {
					indiceMejor = i;
				}
			}
			System.out.printf("%-8s promedio %.2f | mejor: %s (%.1f)%n", evaluaciones[j],
					suma / estudiantes.length,
					estudiantes[indiceMejor], notas[indiceMejor][j]);
		}
	}

	// Paso 4: burbuja sobre el vector de posiciones, no sobre promedios.
	public static int[] calcularOrden(double[] promedios) {
		int[] orden = new int[promedios.length];
		for (int i = 0; i < orden.length; i++) {
			orden[i] = i;
		}
		for (int i = 0; i < orden.length - 1; i++) {
			for (int j = 0; j < orden.length - 1 - i; j++) {
				if (promedios[orden[j]] < promedios[orden[j + 1]]) {
					int temp = orden[j];
					orden[j] = orden[j + 1];
					orden[j + 1] = temp;
				}
			}
		}
		return orden;
	}

	public static void imprimirRanking(String[] estudiantes, double[] promedios, int[] orden) {
		System.out.println();
		System.out.println("=== RANKING ===");
		for (int p = 0; p < orden.length; p++) {
			System.out.printf("%d. %-10s %.2f%n", p + 1, estudiantes[orden[p]], promedios[orden[p]]);
		}
	}

	public static double promedioPonderado(double[] notasEst, double[] pesos) {
		
		 if (notasEst.length != pesos.length) {
		        throw new IllegalArgumentException(
		                "Las notas y los pesos deben tener el mismo tamaño");
		    }
		
        double promedio = 0;
        for (int i = 0; i < notasEst.length; i++) {
            promedio += notasEst[i] * pesos[i];
        }
        return promedio;
    }

    public static Estado evaluar(double promedio, double asistencia) {
    	
    		if (asistencia < 0 || asistencia > 1) {
            throw new IllegalArgumentException("La asistencia debe estar entre 0 y 1");
        }
        if (promedio < 0 || promedio > 5) {
            throw new IllegalArgumentException("El promedio debe estar entre 0 y 5");
        }
    	
        if (asistencia < ASISTENCIA_MINIMA) {
            return Estado.REPROBADO;
        } else if (promedio >= NOTA_APROBACION) {
            return Estado.APROBADO;
        } else if (promedio >= NOTA_RECUPERACION) {
            return Estado.RECUPERACION;
        } else {
            return Estado.REPROBADO;
        }
    }

}