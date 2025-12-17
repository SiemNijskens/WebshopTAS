import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import './App.css'
import Router from './router'

export const API_URL = "http://localhost:8080";

function App() {
  const queryClient = new QueryClient();

  return (
    <QueryClientProvider client={queryClient}>
      <Router />
    </QueryClientProvider>
  )
}

export default App
