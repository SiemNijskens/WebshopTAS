// components/forms/FormField.tsx

import type { FormFieldProps } from "../../types/form.types";

export function FormField({ 
  label, 
  name, 
  type = "text", 
  value, 
  onChange 
}: FormFieldProps) {
  const id = `field-${name}`;
  
  return (
    <div className="form-field">
      <label htmlFor={id}>{label}</label>
      <input 
        id={id} 
        name={name} 
        type={type} 
        value={value} 
        onChange={onChange} 
      />
    </div>
  );
}