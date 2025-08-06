export interface Task {
  id: number,
  title: string;
  description: string;
  status: string;
}

export type TaskStatus = "TO_DO" | "IN_PROGRESS" | "DONE";