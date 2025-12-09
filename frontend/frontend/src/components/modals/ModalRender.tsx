import CustomModal from "./CustomModal";
import type { ModalState } from "../../../frontend/src/types/modal.types";

interface ModalRendererProps {
  modal: ModalState | null;
  onClose: () => void;
}

export function ModalRenderer({ modal, onClose }: ModalRendererProps) {
  if (!modal) return null;

  return (
    <CustomModal
      isOpen={modal.isOpen}
      onClose={onClose}
      title={modal.title}
      footer={modal.footer}
      size={modal.size}
      overlayStyle={modal.overlayStyle}
    >
      {modal.content}
    </CustomModal>
  );
}