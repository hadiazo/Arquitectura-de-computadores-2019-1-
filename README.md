# Arquitectura-de-computadores-2019-1

### Manual de usuario del Desensamblador
1. Coloque las instrucciones en hexadecimal en el panel izquierdo, cada una separada por un salto de línea.
2. Presione "Continuar" para obtener la salida en el panel derecho.
3. Se recomienda guardar el texto de salida en un archivo .csv, pues al abrirlo en Excel puede ser visto y utilizado con más facilidad.

### Manual de usuario del Ensamblador
Este manual se encuentra en la carpeta Ensamblador/dist. No lo borre.

1. Copie las columnas de la plantilla dada en clase, desde HLL hasta ADDR (hex).
2. Pegue el contenido en un archivo de texto .txt y guárdelo.
3. Abra el programa y presione "Buscar archivo". De inmediato se abrirá un explorador que le permitirá localizar el fichero guardado en el paso anterior. Si este es legible, su contenido se podrá visualizar de inmediato en el área de texto de la izquierda.
4. Presione "Continuar". Si siguió las recomendaciones anteriores, los números en hexadecimal saldrán en el formato adecuado para programar en C++ y VHDL, y estarán escritos en los archivos "output_cpp.txt" y "output_vhdl.txt", respectivamente.

Si tuvo errores en la ejecución del programa, por favor tenga en cuenta lo siguiente:
- En la primera fila es opcional colocar ".code" en la columna "Section", pero es obligatorio que esté ".data" justo en la misma fila donde se halle la primera variable o constante.
- Se recomienda que no pegue en el archivo de texto filas vacías o carentes de contenido importante.
- Deje únicamente un salto de línea al final del archivo de texto. De lo contrario se generará un error de lectura.
