// components/modal/CustomModal.tsx

import "../../styles/modals/CustomModal.css";
import Modal from "react-modal";
import type { ReactNode } from "react";
import type { ModalSize, OverlayStyle } from "../../types/modal.types";

interface CustomModalProps {
  isOpen: boolean;
  onClose: () => void;
  title?: string;
  footer?: ReactNode;
  size?: ModalSize;
  overlayStyle?: OverlayStyle;
  children: ReactNode;
}

interface ModalHeaderProps {
  title?: string;
  onClose: () => void;
}

function ModalHeader({ title, onClose }: ModalHeaderProps) {
  return (
    <div className="custom-modal-header">
      {title && <h2 className="custom-modal-title">{title}</h2>}
      <button
        onClick={onClose}
        aria-label="Close modal"
        className="custom-modal-close-btn"
        type="button"
      >
        ×
      </button>
    </div>
  );
}

export default function CustomModal({
  isOpen,
  onClose,
  title,
  footer,
  size = "medium",
  overlayStyle = "dark",
  children,
}: CustomModalProps) {
  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onClose}
      overlayClassName={`custom-modal-overlay overlay-${overlayStyle}`}
      className={`custom-modal-content size-${size}`}
      ariaHideApp={false}
    >
      <ModalHeader title={title} onClose={onClose} />
      <div className="custom-modal-body">{children}</div>
      {footer && <div className="custom-modal-footer">{footer}</div>}
    </Modal>
  );
}