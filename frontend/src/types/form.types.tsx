// types/form.types.ts

export interface SignUpFormData {
  name: string;
  email: string;
  password: string;
  confirmPassword: string;
  zipcode: string;
  streetcode: string;
  houseNumber: string;
  streetname: string;
}

export interface FormFieldProps {
  label: string;
  name: string;
  type?: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export interface SignUpFormProps {
  onSubmit: (data: SignUpFormData) => void;
  onCancel?: () => void;
}