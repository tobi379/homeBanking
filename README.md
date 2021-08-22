#  <a href="https://hombanking.herokuapp.com/index.html" style="text-align: center">Click para ver HomeBanking en VIVO</a>
<h2 align="center">Mi primer proyecto en Java</h2>

El proyecto esta basado en una plataforma de HomeBanking ultizando HTML5, CSS3, JavaScript(VUE3, AJAX, AXIOS),BootStrap y JAVA , en la que se pueden realizar:

<h2 align="center">Inicio de Sesion y Registro</h2>


<p align="center">
  <img src="https://raw.githubusercontent.com/Tecnochii/HomeBanking/main/assets/Captura1.PNG" width="400" title="Login" >
  <img src="https://raw.githubusercontent.com/Tecnochii/HomeBanking/main/assets/Captura.PNG" width="400" title="Register">
</p>

<h2 align="center">Crear y Eliminar Cuentas</h2>
<p>Se pueden crear hasta un maximo de tres cuentas, las cuentas pueden ser de tipo Ahorro o Corriente. Las cuentas se crean con un boton haciendo que aparezca un modal con dos botones cada uno con el tipo de cuenta. 
Si ya hay tres cuentas creadas el boton desaparece.<br>
  Las cuentas pueden ser eliminadas por el Cliente presionando el boton "Delete". Las cuentas se eliminan solo para el Cliente, y persisten en la base de datos como inactivas. 
</p>    

<p align="center">
  <img src="https://raw.githubusercontent.com/Tecnochii/HomeBanking/main/assets/CapturaAcc1.PNG" width="400" title="Crear Cuenta" >
  <img src="https://raw.githubusercontent.com/Tecnochii/HomeBanking/main/assets/CapturaAcc2.PNG" width="400" title="Modal Con Los 2 tipos de cuenta">
  <img src="https://raw.githubusercontent.com/Tecnochii/HomeBanking/main/assets/Captura2.PNG" width="400" title="3 cuentas y desparece el boton" >
</p>

<h2 align="center">Realizar Transacciones</h2>
<p>Se puede realizar transacciones tanto entre las Cuentas propias del Cliente como a cuentas de Terceros(Cuentas de otros clientes registrados).<br>
  Solo se puede realizar la transaccion si el monto a enviar no es menor al monto de la Cuenta de origen.<br>
 Se puede ver el historial de las transacciones que realizo cada cuenta clickeando sobre "See transactions" en la tarjeta de la cuenta a ver.Las transacciones de dividen en dos tipos "Credito" y "Debito", en el historial de las transacciones se las puede diferenciar por "Rojo" y "Verde".<br> 
  Las transacciones actualizan el balance de la cuenta. En la ultima columna del historial se registra el balance con el que quedo la cuenta luego de que se haya realizado la transaccion

</p>   
  <p align="center">
  <img src="https://raw.githubusercontent.com/Tecnochii/HomeBanking/main/assets/Captura7.PNG" width="400" title="Transferencia entre cuentas del Cliente" >
  <img src="https://raw.githubusercontent.com/Tecnochii/HomeBanking/main/assets/Captura8.PNG" width="400" title="Transferencia a cuenta de terceros">
  <img src="https://raw.githubusercontent.com/Tecnochii/HomeBanking/main/assets/Captura4.PNG" width="400" title="Historial de transacciones de una cuenta" >
</p>

                                                
