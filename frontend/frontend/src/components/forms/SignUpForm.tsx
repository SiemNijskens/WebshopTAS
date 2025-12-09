import { useState } from "react";

interface SignUpFormData {
  name: string;
  email: string;
  password: string;
  confirmPassword: string;
  zipcode: string;
  streetcode: string;
  houseNumber: string;
  streetname: string;
}

const initialFormData: SignUpFormData = {
  name: "",
  email: "",
  password: "",
  confirmPassword: "",
  zipcode: "",
  streetcode: "",
  houseNumber: "",
  streetname: "",
};

interface SignUpFormProps {
  onSubmit: (data: SignUpFormData) => void;
  onCancel?: () => void;
}

export function SignUpForm({ onSubmit, onCancel }: SignUpFormProps) {
  const [formData, setFormData] = useState<SignUpFormData>(initialFormData);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(formData);
  };

  return (
    <form onSubmit={handleSubmit} className="signup-form">
      <FormField label="Name" name="name" value={formData.name} onChange={handleChange} />
      <FormField label="Email" name="email" type="email" value={formData.email} onChange={handleChange} />
      <FormField label="Password" name="password" type="password" value={formData.password} onChange={handleChange} />
      <FormField label="Confirm Password" name="confirmPassword" type="password" value={formData.confirmPassword} onChange={handleChange} />
      <FormField label="Zipcode" name="zipcode" value={formData.zipcode} onChange={handleChange} />
      <FormField label="Street Code" name="streetcode" value={formData.streetcode} onChange={handleChange} />
      <FormField label="House Number" name="houseNumber" value={formData.houseNumber} onChange={handleChange} />
      <FormField label="Street Name" name="streetname" value={formData.streetname} onChange={handleChange} />

      <div className="form-actions">
        {onCancel && <button type="button" onClick={onCancel}>Cancel</button>}
        <button type="submit">Sign Up</button>
      </div>
    </form>
  );
}
