// context/ModalContext.tsx

import { createContext, useState, useCallback, useContext, useEffect } from "react";
import type { ReactNode } from "react";
import { useLocation } from "react-router-dom";
import { ModalRenderer } from "../components/modal/ModalRenderer";
import type { ModalOptions, ModalState, ModalContextValue } from "../types/modal.types";

const ModalContext = createContext<ModalContextValue | undefined>(undefined);

export const useModal = (): ModalContextValue => {
  const context = useContext(ModalContext);
  if (!context) {
    throw new Error("useModal must be used within ModalProvider");
  }
  return context;
};

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
      <ModalRenderer modal={modal} onClose={hideModal} />
    </ModalContext.Provider>
  );
}