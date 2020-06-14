<h1 align="center">Superheroes</h1>

<p align="center">  
Superheroes es una aplicación pequeña de demostración desarrollada bajo Android moderno.
<br>
Este proyecto se centra en el lenguaje Kotlin y la implementación del patrón de diseño MVVM.
<br>
Además de consultar una API para extraer los datos e integrarlos en la base de datos local con Room.
</p>
</br>

<p align="center">
<img src="/imagenes/1.png"/>
<img src="/imagenes/2.png"/>
</p>


## Descargar

Através de [Descargar](https://github.com/guidogonzalez/Superheroes/tree/master/descarga) para descargar la APK.

<img src="/imagenes/superheroes.gif" align="right" width="32%"/>

## Tecnologías & librerías

- Mínimo SDK level 21
- [Kotlin](https://kotlinlang.org/) + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) para asincronia.
- JetPack
  - LiveData - notifica los cambios a las vistas.
  - Lifecycle - disponer de datos de observación cuando cambie el estado del ciclo de vida.
  - ViewModel - posee los datos relacionados con la UI.
  - Room - persistencia en la base de datos local.
- Patrón de diseño
  - MVVM (Model - View - ViewModel)
- [Retrofit2](https://github.com/square/retrofit) - construir el API REST
- [Glide](https://github.com/bumptech/glide) - cargar imagenes.
- [Material-Components](https://github.com/material-components/material-components-android) - Componentes de Material design como Card Views.
- Vistas personalizadas
  - [ProgressView](https://github.com/skydoves/progressview) - Un ProgressView pulido y flexible, totalmente personalizable con animaciones.

## Patrón de diseño

Superheroes está basado en el patrón de diseño MVVM.

![arquitectura](https://miro.medium.com/max/960/1*kWwjlkOEyTV6M7W7tZrs1w.png)

## API abierta

Superheroes utiliza [SuperheroesAPI](https://akabab.github.io/superhero-api/api/) para construir el API REST.

# Licencia
```xml
Diseñado y desarrollado por Guido González

Totalmente libre de uso, solo dar créditos en caso de uso comercial.
```
