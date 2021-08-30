package uy.edu.ucu.aed2;

public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;

	/**
	 * Punto de entrada al clasificador
	 * 
	 * @param metodoClasificacion
	 * @param orden
	 * @param tamanioVector
	 * @return Un vector del tam. solicitado, ordenado por el algoritmo solicitado
	 */
	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
			case METODO_CLASIFICACION_INSERCION:
				return ordenarPorInsercion(datosParaClasificar);
			case METODO_CLASIFICACION_SHELL:
				return ordenarPorShell(datosParaClasificar);
			case METODO_CLASIFICACION_BURBUJA:
				return ordenarPorBurbuja(datosParaClasificar);
			default:
				System.err.println("Este codigo no deberia haberse ejecutado");
				break;
		}
		return datosParaClasificar;
	}

	private void intercambiar(int[] vector, int pos1, int pos2) {
		int temp = vector[pos2];
		vector[pos2] = vector[pos1];
		vector[pos1] = temp;
	}

	private int[] ordenarPorShell(int[] datosParaClasificar) {
		int j;
		int inc;
		int[] incrementos = new int[] { 3223, 358, 51, 10, 3, 1 };

		for (int posIncrementoActual = 1; posIncrementoActual < incrementos.length; posIncrementoActual++) {
			inc = incrementos[posIncrementoActual];
			if (inc < (datosParaClasificar.length / 2)) {
				for (int i = inc; i < datosParaClasificar.length; i++) {
					j = i - inc;
					while (j >= 0) {
						if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
							intercambiar(datosParaClasificar, j, j + inc);
						}
						j--;
					}
				}
			}
		}
		return datosParaClasificar;
	}

	protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			for (int i = 1; i < datosParaClasificar.length; i++) {
				int j = i;
				while ((j > 0) && (datosParaClasificar[j] < datosParaClasificar[j - 1])) {
					intercambiar(datosParaClasificar, j, j - 1);
					j--;
				}
			}
			return datosParaClasificar;
		}
		return null;
	}

	private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			int n = datosParaClasificar.length - 1;
			for (int i = 0; i <= n; i++) {
				for (int j = n; j >= (i + 1); j--) {
					if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
						intercambiar(datosParaClasificar, j - 1, j);
					}
				}
			}
			return datosParaClasificar;
		}
		return null;
	}

	public boolean estaOrdenado(int[] vector, boolean asc) {
		for (int i = 1; i < vector.length; i++) {
			if (vector[i - 1] > vector[i] && asc || vector[i - 1] < vector[i] && !asc) {
				return false;
			}
		}
		return true;
	}

	public long calcularTiempoDeEjecucion(int[] datosParaClasificar, int metodoClasificacion) {
		int[] datosCopy = datosParaClasificar.clone();

		// Ordeno según método indicado, almacenando el tI y tF al inicio y final de la ordenación
		long tI = java.lang.System.nanoTime();
		int[] datosSorted = clasificar(datosCopy, metodoClasificacion);
		long tF = java.lang.System.nanoTime();
		long tiempo = tF - tI;

		/* Si el vector está ordenado, devuelve el tiempo insumido
		De lo contrario, devuelve -1 */
		if (estaOrdenado(datosSorted, true)) {
			return tiempo;
		}
		return -1;
	}

	public static void main(String args[]) {

		TClasificador clasif = new TClasificador();

		GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
		int[] vectorAleatorio = gdg.generarDatosAleatorios();
		int[] vectorAscendente = gdg.generarDatosAscendentes();
		int[] vectorDescendente = gdg.generarDatosDescendentes();

		int metodoClasificacion;
		long tiempoDeEjecucion;

		// ORDENACIÓN POR INSERCIÓN
		metodoClasificacion = METODO_CLASIFICACION_INSERCION;
		System.out.println("Tiempos de ejecución para INSERCIÓN:");
		tiempoDeEjecucion = clasif.calcularTiempoDeEjecucion(vectorAleatorio, metodoClasificacion);
		if (tiempoDeEjecucion > 0) {
			System.out.println("• Orden Aleatorio:   " + tiempoDeEjecucion);
		}
		tiempoDeEjecucion = clasif.calcularTiempoDeEjecucion(vectorAscendente, metodoClasificacion);
		if (tiempoDeEjecucion > 0) {
			System.out.println("• Orden Ascendente:  " + tiempoDeEjecucion);
		}
		tiempoDeEjecucion = clasif.calcularTiempoDeEjecucion(vectorDescendente, metodoClasificacion);
		if (tiempoDeEjecucion > 0) {
			System.out.println("• Orden Descendente: " + tiempoDeEjecucion);
		}
		System.out.println("====================================");

		// ORDENACIÓN POR SHELL
		metodoClasificacion = METODO_CLASIFICACION_SHELL;
		System.out.println("Tiempos de ejecución para SHELL:");
		tiempoDeEjecucion = clasif.calcularTiempoDeEjecucion(vectorAleatorio, metodoClasificacion);
		if (tiempoDeEjecucion > 0) {
			System.out.println("• Orden Aleatorio:   " + tiempoDeEjecucion);
		}
		tiempoDeEjecucion = clasif.calcularTiempoDeEjecucion(vectorAscendente, metodoClasificacion);
		if (tiempoDeEjecucion > 0) {
			System.out.println("• Orden Ascendente:  " + tiempoDeEjecucion);
		}
		tiempoDeEjecucion = clasif.calcularTiempoDeEjecucion(vectorDescendente, metodoClasificacion);
		if (tiempoDeEjecucion > 0) {
			System.out.println("• Orden Descendente: " + tiempoDeEjecucion);
		}
		System.out.println("====================================");

		// ORDENACIÓN POR BURBUJA
		metodoClasificacion = METODO_CLASIFICACION_BURBUJA;
		System.out.println("Tiempos de ejecución para BURBUJA:");
		tiempoDeEjecucion = clasif.calcularTiempoDeEjecucion(vectorAleatorio, metodoClasificacion);
		if (tiempoDeEjecucion > 0) {
			System.out.println("• Orden Aleatorio:   " + tiempoDeEjecucion);
		}
		tiempoDeEjecucion = clasif.calcularTiempoDeEjecucion(vectorAscendente, metodoClasificacion);
		if (tiempoDeEjecucion > 0) {
			System.out.println("• Orden Ascendente:  " + tiempoDeEjecucion);
		}
		tiempoDeEjecucion = clasif.calcularTiempoDeEjecucion(vectorDescendente, metodoClasificacion);
		if (tiempoDeEjecucion > 0) {
			System.out.println("• Orden Descendente: " + tiempoDeEjecucion);
		}
	}
}
