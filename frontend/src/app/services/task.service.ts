import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Task } from '../models/task.models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private readonly API_URL = 'http://localhost:8080/tasks';

  constructor(private http: HttpClient) {}    

  getAllTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.API_URL);
  }

  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(this.API_URL, {task});
  }

  updateTask(task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.API_URL}/${task.id}`, {task});
  }

  updateTaskStatus(id: number, status: string): Observable<void> {
    return this.http.patch<void>(`${this.API_URL}/${id}`, { "status": status });
  }

  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }

}
