import { useQuery } from "@tanstack/react-query";
import { fetchMe } from "./fetchMe";

export const useMe = () =>
    useQuery({
        queryKey: ['me'],
        queryFn: fetchMe,
        retry: false,
    });