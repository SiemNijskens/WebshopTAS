import { useQuery } from "@tanstack/react-query";
import { useParams } from "react-router";
import type { UserDTO } from "../types/models";
import { API_URL } from "../App";

const UserDetailPage = () => {
    const { id } = useParams<{ id : string }>();
    
    const { data, isLoading, error } = useQuery<UserDTO>({
        queryKey: ["user", id],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/users/${id}`, {
                credentials: "include",
            });

            if (!response.ok) {
                throw new Error("Failed to fetch user");
            }
            return response.json();
        },
        enabled: !!id,
    });

    if (isLoading) return <p>Loading...</p>
    if (error instanceof Error) return <p>Error: {error.message}</p>

    return (
        <div>
            <h2>User {id}</h2>
            <pre>{JSON.stringify(data, null, 2)}</pre>
        </div>
    );
};

export default UserDetailPage;