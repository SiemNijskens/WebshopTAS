import { QueryClient, useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import type { UserSummaryDTO } from "../types/models";
import { API_URL } from "../App";
import { useState } from "react";

const UserList = () => {

    const [userId, setUserId] = useState(NaN)

    const queryClient = useQueryClient();

    const deleteUserMutation = useMutation({
        mutationFn: async () => {
            const response = await fetch(`${API_URL}/users/${userId}`, {
                method: 'DELETE',
                headers: { 'Content-Type': 'application/json' },
            });

            if (!response.ok) {
                throw new Error('Failed to delete user')
            };

            return response.json();
        },

        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["users"] })
                console.log('user deleted successfully')
                setUserId(NaN);
        }
    })


    const {
        data: users,
        isLoading,
        error,
    } = useQuery<UserSummaryDTO[]>({
        queryKey: ["users"],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/users`);
            if (!response.ok) {
                throw new Error("Failed to fetch users");
            }
            return response.json();
        },
    });

    if (isLoading) {
        return <div>Loading users...</div>;
    }

    if (error) {
        return <div style={{ color: "red" }}>Error: {error.message}</div>;
    }

    const deleteUser = (userid) => {
        setUserId(userid)
        deleteUserMutation.mutate()


    }

    return (<>
        <div>
            <h2>Users</h2>
            {users && users.length > 0 ? (
                <ul>
                    {users.map((user: UserSummaryDTO) => (
                        <li key={user.id}>
                             {user.firstName} {user.lastName} <button onClick={()=>deleteUser(user.id)}> delete user </button>
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No users found</p>
            )}
        </div>
    </>
    );
};

export default UserList