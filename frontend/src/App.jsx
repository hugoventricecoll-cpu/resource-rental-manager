import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css'
import Login from './Components/LoginComponents/Login'
import Hub from './Components/Hub'
import Vehiculos from './Components/VehiculosComponents/Vehiculos'
import Salas from './Components/SalasComponents/Salas'
import Personal from './Components/PersonalComponents/Personal'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Login />} />
        <Route path='/hub' element={<Hub />} >
          <Route path='Vehiculos' element={<Vehiculos />} />
          <Route path='Salas' element={<Salas />} />
          <Route path='Personal' element={<Personal />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
