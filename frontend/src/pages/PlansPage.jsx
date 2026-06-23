import React, {useEffect, useState} from 'react'
import { Typography, Table, TableHead, TableRow, TableCell, TableBody } from '@mui/material'

export default function PlansPage(){
  const [items, setItems] = useState([])
  useEffect(()=>{ fetch('/api/plans').then(r=>r.json()).then(setItems) },[])
  return (
    <div>
      <Typography variant="h5">Trainingspläne</Typography>
      <Table>
        <TableHead><TableRow><TableCell>Name</TableCell><TableCell>Beschreibung</TableCell></TableRow></TableHead>
        <TableBody>{items.map(p=> <TableRow key={p.id}><TableCell>{p.name}</TableCell><TableCell>{p.beschreibung}</TableCell></TableRow>)}</TableBody>
      </Table>
    </div>
  )
}
