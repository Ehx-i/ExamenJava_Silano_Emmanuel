package problemaA;

public class Ejercicio1Ventas {
	public static final double META_SEMANAL = 10000.0;
	public static final double FACTOR_ALTA = 1.2;
	public static final double FACTOR_BAJA = 0.8;

	public static void main(String[] args) {
		String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };
		double[] ventas = { 1250.5, 980.0, 1430.75, 760.25, 2100.0, 2650.5, 1890.0 };
		// Tu código aquí

		double total = calcularTotal(ventas);
		double promedio = calcularPromedio(ventas);

		System.out.println("=== REPORTE DE VENTAS SEMANAL ===");
		int diasSobrePromedio = imprimirTabla(dias, ventas, promedio);
		imprimirResumen(dias, ventas, total, promedio, diasSobrePromedio);

	}

	public static void imprimirResumen(String[] dias, double[] ventas, double total, double promedio,
			int diasSobrePromedio) {
		System.out.println("---------------------------------");
		System.out.printf("Total semanal: %.2f%n", total);
		System.out.printf("Promedio diario: %.2f%n", promedio);

		int indiceMejorDia = indiceMayor(ventas);
		int indicePeorDia = indiceMenor(ventas);
		System.out.printf("Mejor dia: %s (%.2f)%n", dias[indiceMejorDia], ventas[indiceMejorDia]);
		System.out.printf("Peor dia: %s (%.2f)%n", dias[indicePeorDia], ventas[indicePeorDia]);
		System.out.println("Dias por encima del promedio: " + diasSobrePromedio);

		if (total >= META_SEMANAL) {
			System.out.printf("Meta cumplida. Superaste la meta por %.2f%n", total - META_SEMANAL);
		} else {
			System.out.printf("Meta no cumplida. Faltaron %.2f%n", META_SEMANAL - total);
		}
	}

	public static int imprimirTabla(String[] dias, double[] ventas, double promedio) {
		System.out.printf("%-10s %12s %s%n", "Dia", "Venta", "Nivel");
		int contador = 0;
		for (int i = 0; i < ventas.length; i++) {
			System.out.printf("%-10s %12.2f %s%n", dias[i], ventas[i], clasificar(ventas[i], promedio));
			if (ventas[i] > promedio) {
				contador++;
			}
		}
		return contador;
	}

	public static double calcularTotal(double[] v) {

		double sum = 0.0;

		for (double venta : v) {
			sum += venta;
		}

		return sum;
	}

	public static double calcularPromedio(double[] v) {
		double promedio = calcularTotal(v) / v.length;
		return promedio;
	}

	public static int indiceMayor(double[] v) {
		int indexMayor = 0;
		for (int i = 1; i < v.length; i++) {
			if (v[i] > v[indexMayor]) {
				indexMayor = i;
			}
		}
		return indexMayor;
	}

	public static int indiceMenor(double[] v) {
		int indexMenor = 0;
		for (int i = 1; i < v.length; i++) {
			if (v[i] < v[indexMenor]) {
				indexMenor = i;
			}
		}
		return indexMenor;
	}

	public static String clasificar(double venta, double promedio) {
		if (venta >= promedio * FACTOR_ALTA) {
			return "ALTA";
		} else if (venta < promedio * FACTOR_BAJA) {
			return "BAJA";
		} else {
			return "NORMAL";
		}
	}

}
