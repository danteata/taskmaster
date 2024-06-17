import { AvatarIcon } from '@radix-ui/react-icons'
import { Avatar, AvatarImage, AvatarFallback } from '@/components/ui/avatar'
import type { AvatarProps } from '@radix-ui/react-avatar'
import { User } from '@/types/user'

type Props = Partial<AvatarProps> & {
  user: User | undefined
}

function UserAvatar({ user, ...avatarProps }: Props) {
  return (
    <Avatar className="relative h-8 w-8" {...avatarProps}>
      <AvatarImage
        src={
          user?.avatarUrl ||
          'https://instagram.fpnq13-1.fna.fbcdn.net/v/t51.2885-19/44884218_345707102882519_2446069589734326272_n.jpg?_nc_ht=instagram.fpnq13-1.fna.fbcdn.net&_nc_cat=1&_nc_ohc=3yECqrWF0dkAX-1fQPX&edm=ALlQn9MBAAAA&ccb=7-5&ig_cache_key=YW5vbnltb3VzX3Byb2ZpbGVfcGlj.2-ccb7-5&oh=00_AfC4YI9GjTczPKHhpu6gUJwwPYXUTESZ1WNE1OrYzfSCZQ&oe=656D360F&_nc_sid=e7f676'
        }
        // fill
        // alt={`${user?.id}'s profile picture`}
        className="rounded-full object-cover"
      />
      <AvatarFallback className="rounded-full">
        <AvatarIcon />
      </AvatarFallback>
    </Avatar>
  )
}

export default UserAvatar
