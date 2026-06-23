import React, {useEffect, useState} from 'react'
import { Typography, Table, TableHead, TableRow, TableCell, TableBody } from '@mui/material'

export default function ProgressPage(){
  const [items, setItems] = useState([])
  useEffect(()=>{ fetch('/api/progress').then(r=>r.json()).then(setItems) },[])
  return (
    <div>
      <Typography variant="h5">Fortschritt</Typography>
      <Table>
        <TableHead><TableRow><TableCell>Exercise</TableCell><TableCell>Gewicht</TableCell><TableCell>Wiederholungen</TableCell><TableCell>Datum</TableCell></TableRow></TableHead>
        <TableBody>{items.map(p=> <TableRow key={p.id}><TableCell>{p.exerciseId}</TableCell><TableCell>{p.gewicht}</TableCell><TableCell>{p.wiederholungen}</TableCell><TableCell>{p.datum}</TableCell></TableRow>)}</TableBody>
      </Table>
    </div>
  )
}
