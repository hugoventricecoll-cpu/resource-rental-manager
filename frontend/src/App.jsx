import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css'
import Login from './Components/LoginComponents/Login'
import Hub from './Components/Hub'
import Vehiculos from './Components/VehiculosComponents/Vehiculos'
import Salas from './Components/SalasComponents/Salas'
import Personal from './Components/PersonalComponents/Personal'
import Alquilacion from './Components/AlquilacionesComponents/Alquilacion'
import { useState } from 'react'
import AdminPanel from './Components/PanelAdmin/AdminPanel'

function App() {

  const [carrito, setCarrito] = useState([])

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Login />} />
        <Route path='/hub' element={<Hub />} >
          <Route path='Vehiculos' element={<Vehiculos setCarrito={setCarrito} carrito={carrito} />} />
          <Route path='Salas' element={<Salas setCarrito={setCarrito} carrito={carrito} />} />
          <Route path='Personal' element={<Personal setCarrito={setCarrito} carrito={carrito} />} />
          <Route path='Alquilacion' element={<Alquilacion setCarrito={setCarrito} carrito={carrito} />} />
          <Route path='AdminPanel' element={< AdminPanel />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
