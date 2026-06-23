import React from 'react'
import { Container, Typography, Grid, Paper } from '@mui/material'
import Dashboard from './pages/Dashboard'

export default function App() {
  return (
    <Container maxWidth="lg">
      <Typography variant="h4" sx={{ my: 3 }}>Fitness Tracker</Typography>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Paper sx={{ p: 2 }}>
            <Dashboard />
          </Paper>
        </Grid>
      </Grid>
    </Container>
  )
}
