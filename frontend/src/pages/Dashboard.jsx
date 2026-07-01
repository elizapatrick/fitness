import React, {useEffect, useState} from 'react'
import { Grid, Card, CardContent, Typography } from '@mui/material'
import { LineChart, Line, XAxis, YAxis, Tooltip, ResponsiveContainer } from 'recharts'

export default function Dashboard(){
  const [stats, setStats] = useState(null)
  const [chart, setChart] = useState({weightSeries: []})
  const [error, setError] = useState('')

  useEffect(()=>{
    const fetchJson = async (url) => {
      const response = await fetch(url)
      const payload = await response.json().catch(() => ({}))
      if (!response.ok) {
        const message = payload?.error || `Request failed: ${response.status}`
        throw new Error(message)
      }
      return payload
    }

    Promise.all([fetchJson('/api/statistics'), fetchJson('/api/progress/chart')])
      .then(([statsData, chartData]) => {
        setStats(statsData)
        setChart(chartData)
        setError('')
      })
      .catch((err) => {
        setError(err.message || 'Backend request failed')
      })
  },[])

  return (
    <div>
      {error && (
        <Typography color="error" sx={{ mb: 2 }}>
          Backend error: {error}
        </Typography>
      )}
      <Grid container spacing={2}>
        <Grid item xs={12} md={4}>
          <Card><CardContent>
            <Typography variant="h6">Anzahl Trainings</Typography>
            <Typography variant="h4">{stats?.countTrainings ?? '-'}</Typography>
          </CardContent></Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card><CardContent>
            <Typography variant="h6">Anzahl Übungen</Typography>
            <Typography variant="h4">{stats?.countExercises ?? '-'}</Typography>
          </CardContent></Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card><CardContent>
            <Typography variant="h6">Gesamtdauer (min)</Typography>
            <Typography variant="h4">{stats?.totalDuration ?? '-'}</Typography>
          </CardContent></Card>
        </Grid>

        <Grid item xs={12}>
          <Card><CardContent style={{height:300}}>
            <Typography variant="h6">Gewichtsverlauf</Typography>
            <ResponsiveContainer width="100%" height="85%">
              <LineChart data={chart.weightSeries}>
                <XAxis dataKey="date" />
                <YAxis />
                <Tooltip />
                <Line type="monotone" dataKey="weight" stroke="#1976d2" />
              </LineChart>
            </ResponsiveContainer>
          </CardContent></Card>
        </Grid>
      </Grid>
    </div>
  )
}
