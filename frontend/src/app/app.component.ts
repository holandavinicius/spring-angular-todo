import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Task, TaskStatus } from './models/task.models'
import { CardComponent } from "./card/card.component";
import { TaskService } from "./services/task.service";
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CardComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  taskStatus = ['TO_DO', 'IN_PROGRESS', 'DONE'];
  
  tasks: Task[] = [];

  constructor(private taskService: TaskService) {}

  ngOnInit(){
    this.taskService.getAllTasks().subscribe({
      next: tasks => this.tasks = tasks,
      error: (err: HttpErrorResponse) => {
        console.error('Erro ao buscar tasks');
        console.error('Status:', err.status);       
        console.error('Mensagem:', err.message);
        console.error('Detalhes:', err.error);
      }
    });
  }

  getTaskByStatus(status: string){
    return this.tasks.filter(t => t.status === status)
  }

  onDrop(event: DragEvent, status: string) {
    event.preventDefault();
    const taskId = Number(event.dataTransfer?.getData("text"));
    const task = this.tasks.find(t => t.id === taskId);
    if (task) {
      task.status = status as TaskStatus;
      this.taskService.updateTaskStatus(task.id, task.status).subscribe({
        error: (err: HttpErrorResponse) => {
          console.error('Atualizar status');
          console.error('Status:', err.status);       
          console.error('Mensagem:', err.message);
          console.error('Detalhes:', err.error);
        }
      });
    }
    const draggingEl = document.querySelector(".dragging");
    if (draggingEl) draggingEl.classList.remove("dragging");

  }

  onDeleteTask(id: number) {
    this.tasks = this.tasks.filter(task => task.id !== id);
  }
}
