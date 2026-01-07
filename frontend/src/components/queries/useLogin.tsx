import { useMutation, useQueryClient } from "@tanstack/react-query"
import { login } from "./auth";

export const useLogin = () => {
    const queryClient = useQueryClient();

    useMutation({
        mutationFn: login,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['me'] });
        },
    });
}