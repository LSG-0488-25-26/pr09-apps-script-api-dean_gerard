# pr09-apps-script-api-dean_gerard

 # Smartphone Usage App (API + Google Apps Script)

Aplicación Android desarrollada en Kotlin que permite gestionar y visualizar datos relacionados con el uso del smartphone. La app se conecta a una API creada con Google Apps Script, que actúa como backend ligero para almacenar y recuperar información.

## Funcionalidades

- Registro e inicio de sesión de usuarios  
- Visualización de datos de uso del smartphone  
- Inserción de nuevos registros  
- Listado de datos almacenados  
- Consulta de estadísticas  
- Comunicación con API mediante Retrofit  

## Arquitectura

El proyecto sigue una estructura basada en MVVM (Model-View-ViewModel):

- Model → Clases de datos (model/)  
- View → Pantallas con Jetpack Compose (views/)  
- ViewModel → Lógica de negocio (viewmodel/)  
- API → Comunicación con backend (api/)  

## Backend (Google Apps Script)

El backend está implementado en:

app/scripts/Codigo.gs

Este script actúa como una API REST que permite:

- Obtener datos  
- Insertar registros  
- Consultar estadísticas  

## Capturas

<img width="202" height="430" alt="image" src="https://github.com/user-attachments/assets/33e65b03-8630-4158-97fd-96a6a5283fa0" />
<img width="203" height="430" alt="image" src="https://github.com/user-attachments/assets/8beee261-eec1-458e-a725-73b6ca31e602" />
<img width="203" height="430" alt="image" src="https://github.com/user-attachments/assets/6cfbe65c-5896-4bcc-9138-eaa6ba2f276a" />
<img width="203" height="430" alt="image" src="https://github.com/user-attachments/assets/eed4e978-108d-4961-a3fa-586cd8ef1bc6" />
<img width="205" height="430" alt="image" src="https://github.com/user-attachments/assets/c585bd24-8100-4242-a5ff-1f5ebbbc13ba" />
<img width="205" height="430" alt="image" src="https://github.com/user-attachments/assets/a3a387f2-3727-4338-8d0f-a0145e60b4f6" />

<img width="372" height="196" alt="image" src="https://github.com/user-attachments/assets/61702037-1d15-4094-b2ac-8cbd9d8677d4" />
