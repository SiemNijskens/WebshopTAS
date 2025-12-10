import { createContext, useState, useCallback, useContext, useEffect } from "react";
import type { ReactNode } from "react";
import { useLocation } from "react-router-dom";
import type { ModalOptions, ModalState } from "../types/modal.types";

interface ModalContextValue {
  showModal: (options: ModalOptions) => void;
  hideModal: () => void;
  isOpen: boolean;
}

const ModalContext = createContext<ModalContextValue | undefined>(undefined);

// Custom hook voor de context - SRP
export const useModal = (): ModalContextValue => {
  const context = useContext(ModalContext);
  if (!context) {
    throw new Error("useModal must be used within ModalProvider");
  }
  return context;
};

// Interne hook voor state - scheiden van provider ipv samen renderen, scheelt tijd in UI betreft laden components.
function useModalState() {
  const [modal, setModal] = useState<ModalState | null>(null);
  const location = useLocation();

  const showModal = useCallback((options: ModalOptions) => {
    setModal({
      isOpen: true,
      ...options,
    });
  }, []);

  const hideModal = useCallback(() => {
    setModal(null);
  }, []);

  // Close modal on route change
  useEffect(() => {
    hideModal();
  }, [location.pathname, hideModal]);

  return { modal, showModal, hideModal };
}

export function ModalProvider({ children }: { children: ReactNode }) {
  const { modal, showModal, hideModal } = useModalState();

  return (
    <ModalContext.Provider value={{ showModal, hideModal, isOpen: !!modal?.isOpen }}>
      {children}
      {/* Modal rendering verplaatst naar apart component */}
      <ModalRenderer modal={modal} onClose={hideModal} />
    </ModalContext.Provider>
  );
}