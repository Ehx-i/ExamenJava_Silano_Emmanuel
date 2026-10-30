package problemaA;

public class Ejercicio1Ventas {


	public static final double META_SEMANAL = 10000.0;

	public static void main(String[] args) {
		String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };
		double[] ventas = { 1250.5, 980.0, 1430.75, 760.25, 2100.0, 2650.5, 1890.0 };

		// Tu código aquí
		
		
		 double total = calcularTotal(ventas);
	        double promedio = calcularPromedio(ventas);

	        System.out.println("=== REPORTE DE VENTAS SEMANAL ===");
	        System.out.printf("%-10s %12s %s%n", "Dia", "Venta", "Nivel");

	        int diasSobrePromedio = 0;
	        for (int i = 0; i < ventas.length; i++) {
	            System.out.printf("%-10s %12.2f %s%n",
	                    dias[i], ventas[i], clasificar(ventas[i], promedio));
	            if (ventas[i] > promedio) {
	                diasSobrePromedio++;
	            }
	        }

	        System.out.println("---------------------------------");
	        System.out.printf("Total semanal: %.2f%n", total);
	        System.out.printf("Promedio diario: %.2f%n", promedio);

	        int mejor = indiceMayor(ventas);
	        int peor = indiceMenor(ventas);
	        System.out.printf("Mejor dia: %s (%.2f)%n", dias[mejor], ventas[mejor]);
	        System.out.printf("Peor dia: %s (%.2f)%n", dias[peor], ventas[peor]);
	        System.out.println("Dias por encima del promedio: " + diasSobrePromedio);

	        if (total >= META_SEMANAL) {
	            System.out.printf("Meta cumplida. Superaste la meta por %.2f%n",
	                    total - META_SEMANAL);
	        } else {
	            System.out.printf("Meta no cumplida. Faltaron %.2f%n",
	                    META_SEMANAL - total);
	        }
		
	}
	
	
	
	 public static double calcularTotal(double[] v) {
	        double suma = 0;
	        for (int i = 0; i < v.length; i++) {
	            suma += v[i];
	        }
	        return suma;
	    }

	    public static double calcularPromedio(double[] v) {
	        return calcularTotal(v) / v.length;
	    }

	    public static int indiceMayor(double[] v) {
	        int idx = 0;
	        for (int i = 1; i < v.length; i++) {
	            if (v[i] > v[idx]) {
	                idx = i;
	            }
	        }
	        return idx;
	    }

	    public static int indiceMenor(double[] v) {
	        int idx = 0;
	        for (int i = 1; i < v.length; i++) {
	            if (v[i] < v[idx]) {
	                idx = i;
	            }
	        }
	        return idx;
	    }

	    public static String clasificar(double venta, double promedio) {
	        if (venta >= promedio * 1.2) {
	            return "ALTA";
	        } else if (venta < promedio * 0.8) {
	            return "BAJA";
	        } else {
	            return "NORMAL";
	        }
	    }
	
	
}
