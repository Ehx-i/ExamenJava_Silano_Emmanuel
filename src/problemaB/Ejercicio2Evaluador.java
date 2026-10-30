package problemaB;

public class Ejercicio2Evaluador {

	enum Estado {
		APROBADO, RECUPERACION, REPROBADO
	}

	public static void main(String[] args) {
		String[] estudiantes = { "Ana", "Luis", "Sofia", "Carlos", "Valentina", "Mateo" };
		String[] evaluaciones = { "Quiz 1", "Quiz 2", "Taller", "Proyecto" };
		double[][] notas = { { 4.5, 4.9, 3.8, 4.2 }, // Ana
				{ 3.5, 4.0, 4.9, 4.0 }, // Luis
				{ 2.5, 3.0, 2.8, 2.6 }, // Sofia
				{ 3.0, 2.5, 3.2, 3.4 }, // Carlos
				{ 5.0, 4.8, 4.6, 4.9 }, // Valentina
				{ 2.0, 2.5, 1.8, 2.2 }
				// Mateo
		};
		double[] pesos = { 0.20, 0.20, 0.25, 0.35 }; // suman 1.0
		double[] asistencia = { 0.95, 0.70, 0.88, 0.80, 1.00, 0.76 }; // 0.95 = 95%
		// Tu código aquí

		// 1. Promedios y estados
		double[] promedios = new double[estudiantes.length];
		Estado[] estados = new Estado[estudiantes.length];

		System.out.println("=== RESULTADOS DEL CURSO ===");
		System.out.printf("%-10s %8s %10s  %s%n", "Estudiante", "Promedio", "Asistencia", "Estado");
		for (int i = 0; i < estudiantes.length; i++) {
			promedios[i] = promedioPonderado(notas[i], pesos);
			estados[i] = evaluar(promedios[i], asistencia[i]);
			System.out.printf("%-10s %8.2f %10.0f%%  %s%n", estudiantes[i], promedios[i], asistencia[i] * 100,
					estados[i]);
		}

		// 2. Conteo por estado
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
				"Aprobados: " + aprobados + " | Recuperacion: " + recuperacion + " | Reprobados: " + reprobados);

		// 3. Analisis por evaluacion (recorrido por columnas)
		System.out.println();
		System.out.println("=== ANALISIS POR EVALUACION ===");
		for (int j = 0; j < evaluaciones.length; j++) {
			double suma = 0; // se reinicia en cada columna
			int mejor = 0;
			for (int i = 0; i < estudiantes.length; i++) {
				suma += notas[i][j];
				if (notas[i][j] > notas[mejor][j]) {
					mejor = i;
				}
			}
			double promedioCurso = suma / estudiantes.length;
			System.out.printf("%-8s promedio %.2f | mejor: %s (%.1f)%n", evaluaciones[j], promedioCurso,
					estudiantes[mejor], notas[mejor][j]);
		}

		// 4. Ranking con burbuja sobre el vector de posiciones
		int[] orden = new int[estudiantes.length];
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

		System.out.println();
		System.out.println("=== RANKING ===");
		for (int p = 0; p < orden.length; p++) {
			System.out.printf("%d. %-10s %.2f%n", p + 1, estudiantes[orden[p]], promedios[orden[p]]);
		}
	}

	public static double promedioPonderado(double[] notasEst, double[] pesos) {
		double suma = 0;
		for (int i = 0; i < notasEst.length; i++) {
			suma += notasEst[i] * pesos[i];
		}
		return suma;
	}

	public static Estado evaluar(double promedio, double asistencia) {
		if (asistencia < 0.75) {
			return Estado.REPROBADO;
		} else if (promedio >= 3.0) {
			return Estado.APROBADO;
		} else if (promedio >= 2.5) {
			return Estado.RECUPERACION;
		} else {
			return Estado.REPROBADO;
		}
	}

}
