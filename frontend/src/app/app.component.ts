import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Task, TaskStatus} from './models/task.models'

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ RouterOutlet ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  taskStatus = ['To Do', 'In Progress', 'Done'];
  
  tasks: Task[] = [
    { id: 1, title: 'Task1', description: 'My first task', status: "To Do" },
    { id: 2, title: 'Task2', description: 'My second task', status: "In Progress" },
    { id: 3, title: 'Task3', description: 'My third task', status: "Done" },
  ];

  getTaskByStatus(status: string){
    return this.tasks.filter(t => t.status === status)
  }

  onDrop(event: DragEvent, status: string) {
    event.preventDefault();
    const taskId = Number(event.dataTransfer?.getData("text"));
    const task = this.tasks.find(t => t.id === taskId);
    if (task) {
      task.status = status as TaskStatus;
    }
    const draggingEl = document.querySelector(".dragging");
    if (draggingEl) draggingEl.classList.remove("dragging");
  }

  onDragStart(event: DragEvent, task: Task) {
    event.dataTransfer?.setData("text", task.id.toString());
    (event.target as HTMLElement).classList.add("dragging");
  }

  onDragEnd(event: DragEvent) {
    (event.target as HTMLElement).classList.remove("dragging");
  }
}
