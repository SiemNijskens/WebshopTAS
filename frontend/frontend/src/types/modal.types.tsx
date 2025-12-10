import type { ReactNode } from "react";

export type ModalSize = "small" | "medium" | "large";
export type OverlayStyle = "light" | "dark";

export interface ModalOptions {
  title?: string;
  footer?: ReactNode;
  size?: ModalSize;
  overlayStyle?: OverlayStyle;
  content?: ReactNode;  // Hernoemd van 'context'
}

export interface ModalState extends ModalOptions {
  isOpen: boolean;
}