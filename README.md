# Refactorización y Análisis de Calidad con SonarQube


---
# Objetivo del Laboratorio

El objetivo del laboratorio fue identificar problemas de mantenibilidad y Code Smells mediante SonarQube, aplicar técnicas de refactorización y comparar las métricas antes y después de las mejoras realizadas.

---

# Code Smells Identificados Inicialmente

Durante el primer análisis se detectaron múltiples problemas de diseño y mantenibilidad en la clase `PedidoService`.

## Smells encontrados

- Long Method en `procesarPedido()`
- Primitive Obsession por exceso de parámetros primitivos
- Responsabilidades mezcladas dentro de `PedidoService`
- Alta complejidad ciclomática
- Uso de `@Autowired` en lugar de constructor injection
- Validaciones acopladas al procesamiento
- Lógica de descuentos mezclada con lógica principal
- Métodos extensos y difíciles de mantener

---

# Métricas Iniciales SonarQube

| Métrica | Primer Análisis |
|---|---|
| Issues Totales | 10 |
| Coverage | 0.0% |
| Duplications | 0.0% |
| Quality Gate | Passed |

---

# Técnicas de Refactorización Aplicadas

Para mejorar la mantenibilidad y reducir los Code Smells se aplicaron las siguientes técnicas:

## Extract Method

Se dividió el método `procesarPedido()` en métodos más pequeños:

- `validarCliente()`
- `crearPedido()`
- `calcularTotal()`
- `aplicarDescuento()`
- `enviarNotificaciones()`
- `generarRespuesta()`

---

## Value Objects

Se eliminaron parámetros primitivos creando objetos especializados:

- `DatosCliente`
- `Direccion`
- `LineaPedido`
- `CodigoDescuento`

Esto permitió reducir el problema de Primitive Obsession.

---

## Constructor Injection

Se eliminó el uso de `@Autowired` y se implementó inyección de dependencias mediante constructor.

---

## Extract Class

Se creó la clase independiente:

```text
NotificationService
```

para separar la responsabilidad de notificaciones del procesamiento principal del pedido.

---

# Comparación de Métricas SonarQube

| Métrica | Primer Análisis | Segundo Análisis | Tercer Análisis |
|---|---|---|---|
| Issues Totales | 10 | 8 | 8 |
| Coverage | 0.0% | 0.0% | 0.0% |
| Duplications | 0.0% | 0.0% | 0.0% |
| Quality Gate | Passed | Failed | Failed |

---

# Evolución del Proyecto

## Primer Análisis

- Código original con múltiples Code Smells
- Método `procesarPedido()` extenso
- Alta complejidad ciclomática
- Primitive Obsession
- Responsabilidades mezcladas

---

## Segundo Análisis

Después de aplicar refactorización:

- Se redujeron issues detectados por SonarQube
- Se implementaron Value Objects
- Se aplicó Constructor Injection
- Se separó la lógica de notificaciones
- Se redujo la complejidad del método principal

---

## Tercer Análisis

En el último análisis:

- Se mantuvo la reducción de issues
- Se mejoró aún más la estructura del método `procesarPedido()`
- El método principal quedó reducido a pocas líneas
- Se consolidó la separación de responsabilidades

El fallo del Quality Gate final corresponde únicamente a la ausencia de cobertura de pruebas automatizadas (Coverage), no a problemas de mantenibilidad o diseño.

---

# Resultados Obtenidos

Después de la refactorización:

- Se redujo la cantidad de issues reportados por SonarQube
- Se disminuyó la complejidad del método `procesarPedido()`
- Se mejoró la mantenibilidad general del sistema
- Se aplicaron principios de Clean Code y separación de responsabilidades
- Se implementó una estructura más modular y reutilizable

---

# Evidencias Visuales

Todas las capturas y evidencias visuales del laboratorio se encuentran en la carpeta:

```text
EvidenciaVisual/
```
---

# Estructura del Proyecto

```text
src/
├── domain/
├── repository/
├── service/
└── domain/valueobjects/
```

---

# Repositorio

El repositorio incluye:

- Código fuente refactorizado
- Historial de commits
- Evidencias visuales
- README documentado
- Comparación de métricas SonarQube