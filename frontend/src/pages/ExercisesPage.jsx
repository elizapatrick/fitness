import React, {useEffect, useState} from 'react'
import { Button, Table, TableBody, TableCell, TableHead, TableRow, Typography } from '@mui/material'

export default function ExercisesPage(){
  const [items, setItems] = useState([])
  useEffect(()=>{ fetch('/api/exercises').then(r=>r.json()).then(setItems) },[])
  const del = (id)=>{ fetch('/api/exercises/'+id, {method:'DELETE'}).then(()=> setItems(i=>i.filter(x=>x.id!==id))) }
  return (
    <div>
      <Typography variant="h5">Übungsverwaltung</Typography>
      <Table>
        <TableHead>
          <TableRow><TableCell>Name</TableCell><TableCell>Muskelgruppe</TableCell><TableCell>Aktionen</TableCell></TableRow>
        </TableHead>
        <TableBody>
          {items.map(e=> (
            <TableRow key={e.id}><TableCell>{e.name}</TableCell><TableCell>{e.muskelgruppe}</TableCell><TableCell><Button color="error" onClick={()=>del(e.id)}>Löschen</Button></TableCell></TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  )
}
