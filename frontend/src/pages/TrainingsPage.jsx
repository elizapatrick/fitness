import React, {useEffect, useState} from 'react'
import { Button, Table, TableBody, TableCell, TableHead, TableRow, Typography } from '@mui/material'

export default function TrainingsPage(){
  const [trainings, setTrainings] = useState([])
  useEffect(()=>{ fetch('/api/trainings').then(r=>r.json()).then(setTrainings) },[])

  const del = (id)=>{ fetch('/api/trainings/'+id, {method:'DELETE'}).then(()=> setTrainings(t=>t.filter(x=>x.id!==id))) }

  return (
    <div>
      <Typography variant="h5">Trainingsverwaltung</Typography>
      <Table>
        <TableHead>
          <TableRow><TableCell>Titel</TableCell><TableCell>Datum</TableCell><TableCell>Dauer</TableCell><TableCell>Aktionen</TableCell></TableRow>
        </TableHead>
        <TableBody>
          {trainings.map(t=> (
            <TableRow key={t.id}>
              <TableCell>{t.titel}</TableCell>
              <TableCell>{t.datum}</TableCell>
              <TableCell>{t.dauer}</TableCell>
              <TableCell><Button color="error" onClick={()=>del(t.id)}>Löschen</Button></TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  )
}
