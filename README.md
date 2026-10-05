# Motor de Documentos Inteligentes

Motor de documentos en Java que integra seis patrones de diseño: Flyweight, Builder, Bridge, Chain of Responsibility, Interpreter y Mediator.

## Ejecución (JDK 17 o superior)

```bash
mkdir out
javac -d out $(find src -name "*.java")
java -cp out motor.Main
```


## Dónde está cada patrón

| Patrón | Clases principales |
|---|---|
| Flyweight | `GlifoFactory`, `Glifo`, `Icono`, `GlifoColocado` (estado extrínseco) |
| Builder | `DocumentBuilder`, `BaseDocumentBuilder`, `ReporteEjecutivoBuilder`, `FacturaSimpleBuilder` |
| Bridge | `Documento` / `DocumentoPaginado` / `DocumentoContinuo` ↔ `RenderizadorEngine` y sus implementaciones |
| Chain of Responsibility | `ProcesadorHandler` y tres manejadores |
| Interpreter | `Expresion`, `ExpresionTerminal`, `ExpresionNoTerminal`, `ExpresionParser` |
| Mediator | `DocumentEditorMediator` y cuatro componentes |

## Salida en pantalla

<img width="742" height="866" alt="image" src="https://github.com/user-attachments/assets/0f8b0719-3f71-4800-bb6d-054c26531b9b" />

<img width="669" height="760" alt="image" src="https://github.com/user-attachments/assets/44ab46c2-cf57-4843-a54f-67039b76efdc" />

<img width="586" height="333" alt="image" src="https://github.com/user-attachments/assets/079315ce-158d-4c30-97f4-b18935e9253f" />




## Demo

`Main` ejecuta: configuración vía Mediator → construcción con Builder y Flyweights → cadena de procesamiento → evaluación de `#{PRECIO_BASE * 1.19 - DESCUENTO}` → exportación en PDF, HTML y Markdown. Un cuarto caso muestra la interrupción de la cadena ante un marcador corrupto.
