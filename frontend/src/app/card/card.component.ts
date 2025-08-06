import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Task, TaskStatus} from '../models/task.models'
import { TaskService } from "../services/task.service";
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-card',
  imports: [],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {
  @Input() task!: Task;
  @Output() delete = new EventEmitter<number>();

  constructor(private taskService: TaskService) {}

  onDragStart(event: DragEvent, task: Task) {
    event.dataTransfer?.setData("text", task.id.toString());
    (event.target as HTMLElement).classList.add("dragging");
  }

  onDragEnd(event: DragEvent) {
    (event.target as HTMLElement).classList.remove("dragging");
  }

  clickDeleteButton(){
    this.taskService.deleteTask(this.task.id).subscribe({
    next: () => {
        console.log('Task deletada com sucesso');
        this.delete.emit(this.task.id);
      },
      error: (err) => {
        console.error('Erro ao deletar task', err);
      }
    });
  }
}

