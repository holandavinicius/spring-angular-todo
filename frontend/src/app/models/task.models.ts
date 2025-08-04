export interface Task {
  id: number,
  title: string;
  description: string;
  status: TaskStatus;
}

export type TaskStatus = "To Do" | "In Progress" | "Done";